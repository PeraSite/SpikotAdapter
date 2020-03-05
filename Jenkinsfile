pipeline{
    agent any

    triggers{
        upstream(
            upstreamProjects: 'Spikot/Spikot/master',
            threshold: hudson.model.Result.SUCCESS
        )
    }

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
            archiveArtifacts artifacts: '**/libs/*-Plugin.jar', fingerprint: true
        }
    }
}