val scalaJsReactVersion = "0.9.1"

lazy val root = project.in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    Seq(
      name := "candidates",
      scalaVersion := "2.11.7",
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core" % scalaJsReactVersion,
        "com.github.japgolly.scalajs-react" %%% "extra" % scalaJsReactVersion
      ),
      jsDependencies +=
        "org.webjars" % "react" % "0.12.2" / "react.min.js" commonJSName "React",
      persistLauncher in Compile := true,
      persistLauncher in Test := false,
      artifactPath in(Compile, fullOptJS) := baseDirectory.value / "candidates.js",
      artifactPath in(Compile, packageScalaJSLauncher) := baseDirectory.value / "candidates-launcher.js",
      artifactPath in(Compile, packageJSDependencies) := baseDirectory.value / "candidates-jsdeps.js"
    ) ++ scalariformSettings
  )


