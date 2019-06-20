pipeline{
        agent any
        stages{
                stage('---clean---'){
                        steps{
                                sh "mvn clean -f /var/lib/jenkins/workspace/pipeline/SoloProject/"
                        }
                }
                stage('--test--'){
                        steps{
                                sh "mvn test"
                        }
                }
                stage('--package--'){
                        steps{
                                sh "mvn package"
                        }
                }
		stage('--sonar--'){
                        steps{
                                sh "mvn sonar:sonar"
                        }
                }
		stage('--verify--'){
                        steps{
                                sh "mvn verify"
                        }
                }
		stage('--surefire--'){
                        steps{
                                sh "mvn surefire-report:report"
				sh "mvn site"
                        }
                }
		stage('--deploy--'){
                        steps{
                                sh "cd /"
				sh "pwd"
				sh "sudo cp SoloProject/KingGizzAPI/target/KingGizzAPI.war /home/jamesmclark96/scripts/wildfly-10.1.0.Final/standalone/deployments/"
                        }
                }
        }
}