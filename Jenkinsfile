pipeline{
    agent any

    environment{
        MAVEN_CREDENTIAL = credentials('heartpattern-maven-repository')
    }

    stages{
        stage('build'){
            steps{
                sh './gradlew -PnexusUser=${MAVEN_CREDENTIAL_USR} -PnexusPassword=${MAVEN_CREDENTIAL_PSW} --no-daemon createPlugin'
            }
        }
    }
    post{
        always{
            archiveArtifacts artifacts: '**/libs/*', fingerprint: true
        }
    }
}