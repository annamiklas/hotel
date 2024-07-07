pipeline {

    agent any

    tools {
        maven 'maven 3'
    }

    stages {
        stage("build") {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }

        stage("test") {
            steps {
                echo 'Testing ...'
            }
        }
    }

}