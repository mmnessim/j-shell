#! /bin/bash

./gradlew build
if [ $? -ne 0 ]; then
    echo "Build failed. Please check the errors above."
    exit 1
fi
java -cp app/build/classes/java/main org.mnessim.App
