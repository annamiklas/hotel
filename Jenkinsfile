pipeline {

    agent any

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