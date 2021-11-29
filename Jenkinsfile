pipeline {
    agent any
    stages {
        stage ('Build') {
            steps {
                sh 'mvn clean install' 
                archiveArtifacts(artifacts: 'target/SynesthesiaSwitcher-*.jar', allowEmptyArchive: true, fingerprint: true, onlyIfSuccessful: true)
            }
        }
    }
}
