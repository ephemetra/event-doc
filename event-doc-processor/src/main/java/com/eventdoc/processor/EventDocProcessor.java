package com.eventdoc.processor;

import com.eventdoc.annotation.EventDoc;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

//@AutoService(Processor.class)
@SupportedAnnotationTypes("com.eventdoc.annotation.EventDoc")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class EventDocProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "哈哈");
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 遍历找到项目中所有的@EventDoc注解
        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                // 对每个被注解的元素调用processEventDoc方法进行处理
                processEventDoc(element);
            }
        }
        // 返回true表示这些注解已被处理，不需要其他处理器再处理
        return true;
    }

    private void processEventDoc(Element element) {
        EventDoc eventDoc = element.getAnnotation(EventDoc.class);
        String classComment = getClassComment(element);
        String[] fieldComment = getFieldComment(element);
        // 写入文件
        FileObject file = null;
        try {
            file = processingEnv.getFiler()
                    .createResource(StandardLocation.SOURCE_OUTPUT, "", "GeneratedDoc.txt");
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "生成文件路径: " + file.toUri());
            try (Writer writer = file.openWriter()) {
                writer.write("test");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取类元素的注释
     */
    public String getClassComment(Element element) {
        String comment = processingEnv.getElementUtils().getDocComment(element);
        return comment != null ? comment.trim() : "";
    }

    /**
     * 获取类元素下面的字段注释
     */
    public String[] getFieldComment(Element element) {
        return element.getEnclosedElements().stream()
                .filter(enclosed -> enclosed.getKind() == ElementKind.FIELD)
                .map(enclosed -> {
                    String comment = processingEnv.getElementUtils().getDocComment(enclosed);
                    return comment != null ? comment.trim() : "";
                })
                .filter(comment -> !comment.isEmpty())
                .toArray(String[]::new);
    }
}