///usr/bin/env jbang "$0" "$@" ; exit $?

//DEPS io.quarkus:quarkus-bootstrap-app-model:3.3.0
//JAVA 17

import java.io.FileInputStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

import io.quarkus.bootstrap.BootstrapConstants;
import io.quarkus.bootstrap.model.ApplicationModel;
import io.quarkus.bootstrap.resolver.AppModelResolverException;
import io.quarkus.maven.dependency.ArtifactKey;
import io.quarkus.maven.dependency.GACT;
import io.quarkus.maven.dependency.ResolvedDependency;

public class QuarkusAppMain {
    public static void main(String[] args) throws Exception {
        try (InputStream existing = new FileInputStream("app1/build/tmp/quarkusDev/quarkus-app-model.dat");
                ObjectInputStream object = new ObjectInputStream(existing)) {
            ApplicationModel model = (ApplicationModel) object.readObject();

            System.out.println("== App Artifact:");
            System.out.println(model.getAppArtifact());

            System.out.println("== Dependencies:");
            for (ResolvedDependency d : model.getDependencies()) {
                System.out.println(d);
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new AppModelResolverException("Failed to deserialize quarkus model", e);
        }
        try (FileInputStream input = new FileInputStream("app1/build/tmp/quarkusDev/quarkus-app-model.dat")) {

        }
    }
}
