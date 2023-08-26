///usr/bin/env jbang "$0" "$@" ; exit $?

//DEPS io.smallrye:jandex:3.1.2
//JAVA 17

import java.io.FileInputStream;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.Index;
import org.jboss.jandex.IndexReader;

public class JandexMain {
    public static void main(String[] args) throws Exception {
        try (FileInputStream input = new FileInputStream("core-lib/build/resources/main/META-INF/jandex.idx")) {
            IndexReader reader = new IndexReader(input);
            Index index = reader.read();

            // java.util.Collection<ClassInfo> knownClasses = index.getKnownClasses();
            // System.out.println(knownClasses.size());
            // for (ClassInfo classInfo : knownClasses) {
            //     System.out.println(classInfo.name());
            // }

            System.out.println("--- org.acme.core.CoreProcessor");
            ClassInfo reportingService = index.getClassByName("org.acme.core.CoreProcessor");
            for (AnnotationInstance a : reportingService.annotations()) {
                System.out.println(a.name());
            }

            System.out.println("--- org.acme.core.kt.ConfigService");
            ClassInfo foo = index.getClassByName("org.acme.core.kt.ConfigService");
            for (AnnotationInstance a : foo.annotations()) {
                System.out.println(a.name());
            }
        }
    }
}
