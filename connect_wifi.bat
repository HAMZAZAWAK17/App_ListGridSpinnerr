@echo off
echo ========================================
echo   Connexion Android via WiFi
echo ========================================
echo.

REM Vérifier si ADB est disponible
where adb >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERREUR: ADB n'est pas trouve dans le PATH
    echo Veuillez ajouter le SDK Android au PATH
    echo Chemin typique: C:\Users\%USERNAME%\AppData\Local\Android\Sdk\platform-tools
    pause
    exit /b 1
)

echo Etape 1: Connectez votre telephone par USB
echo Etape 2: Assurez-vous que le debogage USB est active
echo.
pause

echo.
echo Verification des appareils connectes...
adb devices
echo.

echo Redemarrage du serveur ADB en mode TCP sur le port 5555...
adb tcpip 5555
echo.

echo Attendez 5 secondes...
timeout /t 5 /nobreak >nul
echo.

echo Maintenant, DECONNECTEZ le cable USB de votre telephone
echo.
pause

echo.
set /p IP="Entrez l'adresse IP de votre telephone (ex: 192.168.1.100): "
echo.

echo Connexion a %IP%:5555...
adb connect %IP%:5555
echo.

echo Verification de la connexion...
adb devices
echo.

echo ========================================
echo Si vous voyez votre appareil avec "device",
echo la connexion WiFi est reussie!
echo ========================================
echo.
echo Pour executer votre app, utilisez le bouton Run dans Android Studio
echo.
pause
