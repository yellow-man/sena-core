name := """sena-core"""

version := "1.2.2"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "yokohama.yellow_man"       % "common-tools_2.11"             % "1.1.1",
  "mysql"                     % "mysql-connector-java"          % "5.1.38",
  "net.sf.opencsv"            % "opencsv"                       % "2.3",
  "org.apache.httpcomponents" % "httpclient"                    % "4.5.2",
  "org.apache.httpcomponents" % "httpcore"                      % "4.4.6",
  "org.apache.commons"        % "commons-email"                 % "1.4",
  "commons-io"                % "commons-io"                    % "2.5",
  "com.google.apis"           % "google-api-services-calendar"  % "v3-rev180-1.22.0"
)

resolvers += "Maven Repository on Github" at "http://yellow-man.github.io/common-tools/"
