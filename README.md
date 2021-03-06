TrivialDice
============

A trivial dice app for android

git clone https://github.com/Hefezopf/TrivialDice.git


Ubuntu Setup:
-------------
checken
sudo dpkg --list | sudo grep maven

JDK 6 installieren
sudo apt-get install openjdk-6-jdk

ANDROID installieren von www als *.gz nach /opt/android-sdk-linux
In PATH aufnehmen
export PATH=$PATH:/opt/android-sdk-linux/tools
/opt/android-sdk-linux/tools/android update sdk --no-ui --obsolete --force
ANDROID_HOME setzen
export ANDROID_HOME=/opt/android-sdk-linux

Maven 3.0.4+ installieren:
sudo apt-get install maven
Maven testen
mvn -version

JAVA_HOME setzen
export JAVA_HOME=/usr/lib/jvm/java-1.6.0-openjdk-i386

PATH setzen
export PATH=/usr/lib/jvm/java-1.6.0-openjdk-i386/bin:$PATH

Java testen
java -version
javac -version

mvn clean install -DskipTests


Komplette Schritte zum bauen und ausliefern:
--------------------------------------------
start emulator -avd A8 -gpu on -wipe-data -scale 96dpi -dpi-device 160

mvn clean install -DskipTests

evtl.: mvn android:undeploy

mvn android:deploy (Tests werden nicht deployed - Fehler im mvn erscheint -> OK)

mvn integration-test

Erst zum Schluss signen!

mvn package -Psign (-> target/xxx-zipaligned.apk)

APK's (xxx-zipaligned.apk) von Hand in der Webseite hochladen:

https://play.google.com/apps/publish/?dev_acc=12007078229515208860#ApkPlace:p=de.hopf.mobile

-> Button KONFIGURATION DER PRODUKTIONSVERSION Neue APK-Datei in Produktionsphase hochladen

dann:

mvn versions:set -DnewVersion=1.30

2 Properties in der Parent POM einstellen. 
Nur wenn Version im Appstore hochgezählt werden soll!

<android.manifest.versionCode>30</android.manifest.versionCode>

<android.manifest.versionName>1.30</android.manifest.versionName>

mvn android:manifest-update

mvn versions:commit (löscht pom.xml.versionsBackup Files)

Build:
------
vom Parent Dir:

mvn clean package android:delpoy -> /target/<trivialdice-xxx>.apk  -> mit Tests auf dem Emulator!

mvn clean install -DskipTests android:delpoy -> /target/<trivialdice-xxx>.apk  -> mit Tests auf dem Emulator!

mvn install -Psign -> /target/<trivialdice-xxx>-zipaligned.apk

mvn android:undeploy

mvn android:deploy

Oder auch jedes Artifakt einzeln:

Change Dir:

cd TrivialDiceLite

mvn install -Psign

Manifest versions increment:
----------------------------
mvn android:manifest-update (vorher Properties in der Parent POM einstellen)

mvn android:manifest-update -Dandroid.manifest.versionName=1.13 -Dandroid.manifest.versionCode=13

Artifact versions increment:
----------------------------
mvn versions:set -DnewVersion=1.17 

mvn clean install -DskipTests

mvn versions:commit (löscht pom.xml.versionsBackup Files)

mvn versions:set -DnewVersion=1.8-SNAPSHOT

mvn clean install -DskipTests

Upload:
-------
https://play.google.com/apps/publish/?dev_acc=12007078229515208860#ApkPlace:p=de.hopf.mobile

Button KONFIGURATION DER PRODUKTIONSVERSION Neue APK-Datei in Produktionsphase hochladen
 
Run Emulator:
-------------
Left Conrtol + F11 = Landscape (Keyboard off in Emulator!)

emulator -avd <avd_name> -gpu on -wipe-data -scale 96dpi -dpi-device 160

-> also: emulator -avd A8 -gpu on -wipe-data -scale 96dpi -dpi-device 160

oder:
Aus Eclipse heraus oben im Menu das entsprechende Icon klicken

'Wipe user data' anchecken!

oder:

Auf der Console 'android' eingeben.

Dann im Menu den AVD Manager starten.

AVD A8 oder Nexus 10 mit Android 2.2 starten (evtl 'wipe user data' an checken)

Dann aus Eclipse rechte Maus auf Projekt und 'run as Anrdoid Application'

Android JUnit Tests:

Eclipse rechte Maus auf Test Projekt und 'run as Anrdoid JUnit Test'

Eclipse Git:
------------
secure PW:*

Jenkins:
--------
Jenkins ist als lokaler Dienst unter Windows installiert.

Nach jedem Checkin läuft er los!

Evtl. ist dieser Dienst ausgeschaltet.

http://localhost:8080/

