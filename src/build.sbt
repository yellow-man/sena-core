name := """sena-core"""

version := "1.3.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "yokohama.yellow_man"       % "common-tools_2.11"             % "1.2.0"
  excludeAll(
    ExclusionRule(organization="org.slf4j", name="slf4j-jdk14"),
    ExclusionRule(organization="org.slf4j", name="slf4j-nop")
  ),
  "ch.qos.logback"            % "logback-classic"               % "1.2.3",
  "org.slf4j"                 % "slf4j-api"                     % "1.7.25",
  "org.slf4j"                 % "jul-to-slf4j"                  % "1.7.25",
  "org.slf4j"                 % "jcl-over-slf4j"                % "1.7.25",
  "mysql"                     % "mysql-connector-java"          % "5.1.38",
  "net.sf.opencsv"            % "opencsv"                       % "2.3",
  "org.apache.httpcomponents" % "httpclient"                    % "4.5.2",
  "org.apache.httpcomponents" % "httpcore"                      % "4.4.6",
  "org.apache.commons"        % "commons-email"                 % "1.4",
  "commons-io"                % "commons-io"                    % "2.5",
  "com.google.apis"           % "google-api-services-calendar"  % "v3-rev180-1.22.0"
)

resolvers += "Maven Repository on Github" at "http://yellow-man.github.io/common-tools/"
