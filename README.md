TrivialDice
===========

A trivial dice app for android

git clone https://github.com/Hefezopf/TrivialDice.git

Build:
------
vom Parent Dir:
mvn clean install -> /target/<trivialdice-xxx>.apk
mvn install -Psign -> /target/<trivialdice-xxx>-zipaligned.apk

Oder auch jedes Artifakt einzeln:
Change Dir:
cd TrivialDiceLite
mvn install -Psign

Upload:
-------
https://play.google.com/apps/publish/?dev_acc=12007078229515208860#ApkPlace:p=de.hopf.mobile
Button KONFIGURATION DER PRODUKTIONSVERSION Neue APK-Datei in Produktionsphase hochladen
 
Run Emulator:
-------------
emulator -avd <avd_name> -gpu on -wipe-data  -> also: emulator -avd A8 -gpu on -wipe-data
oder:
Aus Eclipse heraus oben im Menu das entsprechende Icon klicken
'Wipe user data' anchecken!
oder:
Auf der Console 'android' eingeben.
Dann im Menu den AVD Manager starten.
AVD A8 mit Android 2.2 starten
Dann aus Eclipse rechte Maus auf Projekt und 'run as Anrdoid Application'
Android JUnit Tests:
Eclipse rechte Maus auf Test Projekt und 'run as Anrdoid JUnit Test'


 
