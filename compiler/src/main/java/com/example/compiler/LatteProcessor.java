package com.example.compiler;


import com.example.annotations.AppRegisterGenerator;
import com.example.annotations.EntryGenerator;
import com.example.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * LatteProcessor 注解处理器
 * getSupportedAnnotations() 将所有的注解类加到Set容器中
 * getSupportedAnnotationTypes() 该方法返回字符串的集合表示该处理器用于处理那些注解
 * getSupportedSourceVersion()	该方法用来指定支持的java版本，一般来说我们都是支持到最新版本，因此直接返回SourceVersion.latestSupported(）即可
 * process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) 该方法是注解处理器处理注解的主要地方，我们需要在这里写扫描和处理注解的代码，以及最终生成的java文件。其中需要深入的是RoundEnvironment类，该用于查找出程序元素上使用的注解
 * scan(RoundEnvironment env,Class<? extends Annotation> annotation,AnnotationValueVisitor visitor) 扫描每个类我们注解的东西
 * AnnotationMirror 表示一个注释
 * AnnotationValueVisitor注释类型元素值的 visitor，使用 visitor 设计模式的变体。标准 visitor 基于类型层次结构成员的具体类型进行调度，与它不同，此 visitor 基于存储数据的类型进行调度；对于存储，没有不同的子类，例如 boolean 值和 int 值。在编译时某个值的类型未知时，实现此接口的类被用来对该值进行操作。在将 visitor 传递给某个值的 accept 方法时，适用于该值的 visitXYZ 方法将被调用。
 */

@SuppressWarnings("unused")
@AutoService(Processor.class)
public final class LatteProcessor extends AbstractProcessor {


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        generateEntryCode(env);
        generatePayEntryCode(env);
        generateAppRegisterCode(env);
        return true;
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotation : supportAnnotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }


    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }


    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {

        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {//找到特殊的带注释的元素
            final List<? extends AnnotationMirror> annotationMirrors =
                    typeElement.getAnnotationMirrors();//返回直接存在于此元素上的注释；如果没有，则返回一个空列表

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();//返回该注释元素的值。

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);//将一个 visitor 应用到此元素
                }
            }
        }
    }

    private void generateEntryCode(RoundEnvironment env) {
        final EntryVisitor entryVisitor =
                new EntryVisitor(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVisitor);
    }

    private void generatePayEntryCode(RoundEnvironment env) {
        final PayEntryVisitor payEntryVisitor =
                new PayEntryVisitor(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, payEntryVisitor);
    }

    private void generateAppRegisterCode(RoundEnvironment env) {
        final AppRegisterVisitor appRegisterVisitor =
                new AppRegisterVisitor(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, appRegisterVisitor);
    }
}
