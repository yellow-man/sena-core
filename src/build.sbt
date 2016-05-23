name := """sena-core"""

version := "1.0.0-1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "yokohama.yellow_man"       % "common-tools_2.10"             % "0.0.1",
  "mysql"                     % "mysql-connector-java"          % "5.1.38",
  "org.apache.httpcomponents" % "httpclient"                    % "4.5.2",
  "com.google.apis"           % "google-api-services-calendar"  % "v3-rev180-1.22.0"
)

resolvers += "Maven Repository on Github" at "http://yellow-man.github.io/common-tools/"
