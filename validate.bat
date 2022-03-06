@echo off

set "filePath="
for /f "delims=/" %%a in ('dir /s /b "%1"') Do Set filePath=%%a

if not defined filePath echo Please provide a file that exists in the current directory!

if defined filePath java -jar target/sudoku-validator-1.0.0-jar-with-dependencies.jar "%filePath%"


