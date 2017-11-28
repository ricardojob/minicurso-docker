FROM tomcat
COPY a.txt ${CATALINA_HOME}
COPY app.war ${CATALINA_HOME}/webapps   