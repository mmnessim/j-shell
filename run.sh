#! /bin/bash

if [ $# -eq 0 ]; then
    ./gradlew build -x test
    if [ $? -ne 0 ]; then
        echo "Build failed. Please check the errors above."
        exit 1
    fi
    java -cp app/build/classes/java/main org.mnessim.App
fi

if [ "$1" == "run" ]; then
    java -cp app/build/classes/java/main org.mnessim.App
fi

if [ "$1" == "test" ]; then
    ./gradlew test --info
fi

if [ "$1" == "build" ]; then
    ./gradlew build -x test
    if [ $? -ne 0 ]; then
        echo "Build failed. Please check the errors above."
        exit 1
    fi
fi
