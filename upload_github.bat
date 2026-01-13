@echo off
REM ================================================
REM    GITHUB HOCHLADEN - CosmeticsPlus
REM ================================================

echo.
echo Schritt 1: Git initialisieren...
cd C:\Users\Mac\hytale-cosmetic-mod
if exist .git (
    echo [OK] Git ist bereits initialisiert
) else (
    git init
    echo [OK] Git initialisiert
)

echo.
echo Schritt 2: Alle Dateien hinzufugen...
git add .
echo [OK] Dateien hinzugefugt

echo.
echo Schritt 3: Commit erstellen...
git commit -m "Initial commit - CosmeticsPlus v1.0.0"
echo [OK] Commit erstellt

echo.
echo ===================================================
echo    WICHTIG: GitHub Benutzernamen!
echo ===================================================
echo.
set /p USERNAME="Dein GitHub Benutzernamen: "

echo.
echo Schritt 4: GitHub Repository hinzufugen...
git remote add origin https://github.com/%USERNAME%/hytale-cosmetics-plus.git
echo [OK] Repository hinzugefugt

echo.
echo Schritt 5: Auf main Branch wechseln...
git branch -M main
echo [OK] Branch auf main gesetzt

echo.
echo Schritt 6: Auf GitHub hochladen...
echo.
echo ===================================================
echo    WICHTIG: GitHub Password/Token!
echo ===================================================
echo.
echo Wenn du 2-Faktor-Authentifizierung nutzt,
echo musst du ein Personal Access Token erstellen:
echo.
echo 1. Gehe zu: https://github.com/settings/tokens
echo 2. Klicke "Generate new token" (classic)
echo 3. Gib Token einen Namen und generiere
echo 4. Kopiere den Token
echo.
echo ===================================================
echo.

git push -u origin main

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ===================================================
    echo    ERFOLGREICH! Auf GitHub hochgeladen!
    echo ===================================================
    echo.
    echo Dein Projekt ist jetzt unter:
    echo https://github.com/%USERNAME%/hytale-cosmetics-plus
    echo.
    pause
) else (
    echo.
    echo ===================================================
    echo    FEHLER BEIM HOCHLADEN!
    echo ===================================================
    echo.
    echo Prufe:
    echo 1. Ist dein GitHub Benutzernamen korrekt?
    echo 2. Hast du ein Token/Password benotigt?
    echo 3. Ist das Repository bereits vorhanden?
    echo.
    echo Versuche erneut oder siehe GITHUB_ANLEITUNG.md
    echo.
    pause
)
