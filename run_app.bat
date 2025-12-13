@echo off
echo ========================================
echo   Lancement de l'application Android
echo ========================================
echo.

cd /d "%~dp0"

echo Synchronisation Gradle...
call gradlew.bat --stop
call gradlew.bat clean
echo.

echo Compilation et installation...
call gradlew.bat installDebug
echo.

if %ERRORLEVEL% EQU 0 (
    echo ========================================
    echo   Application installee avec succes!
    echo ========================================
    echo.
    echo Lancement de l'application...
    adb shell am start -n com.example.app_listgridspinnerr/.MainActivity
    echo.
    echo L'application devrait maintenant s'ouvrir sur votre appareil/emulateur
) else (
    echo.
    echo ERREUR: La compilation a echoue
    echo Verifiez les erreurs ci-dessus
)

echo.
pause
