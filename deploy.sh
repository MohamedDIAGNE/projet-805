#!/bin/bash

echo "_________________________Déploiement de l'application______________________________________"

echo "___________________________________mvn clean________________________________________________"
sudo mvn clean

sleep 15

echo "_______________________________________mvn package___________________________________________"
sudo mvn package

sleep 5

echo "************************************************Suppression du war dans le répertoire**************************************"
sudo rm -r /opt/wildfly/standalone/deployments/webapp.war

sleep 20

sudo rm -r /opt/wildfly/standalone/deployments/webapp.war.undeployed 

sleep 10

echo "*************************************************Copie du fichier*********************************************"
sudo cp /home/user/projet-805/webapp/target/webapp.war /opt/wildfly/standalone/deployments/

sleep 20
echo "*************************************************LOG*********************************************"
cat /opt/wildfly/standalone/log/server.log 


