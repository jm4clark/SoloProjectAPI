pipeline{
        agent any
        stages{
		stage('---setup---'){
                        steps{
                                sh "sudo rm -rf /var/lib/wildfly-10.1.0.Final/standalone/deployments/*"
                        }
                }
                stage('---clean---'){
                        steps{
                                sh "mvn clean -f /var/lib/jenkins/workspace/${JOB_NAME}/KingGizzAPI"
                        }
                }
                stage('--test--'){
                        steps{
                                sh "mvn test -f /var/lib/jenkins/workspace/${JOB_NAME}/KingGizzAPI"
                        }
                }
                stage('--package--'){
                        steps{
                                sh "mvn package -f /var/lib/jenkins/workspace/${JOB_NAME}/KingGizzAPI"
                        }
                }
		stage('--sonar--'){
                        steps{
                                sh "mvn sonar:sonar -f /var/lib/jenkins/workspace/${JOB_NAME}/KingGizzAPI"
                        }
                }
		stage('--verify--'){
                        steps{
                                sh "mvn verify -f /var/lib/jenkins/workspace/${JOB_NAME}/KingGizzAPI"
                        }
                }
		stage('--surefire--'){
                        steps{
                                sh "mvn surefire-report:report -f /var/lib/jenkins/workspace/${JOB_NAME}/KingGizzAPI"
				sh "mvn site -f /var/lib/jenkins/workspace/${JOB_NAME}/KingGizzAPI"
                        }
                }
		stage('--deploy--'){
                        steps{
                                sh "cd /"
				sh "pwd"
				sh "sudo cp /var/lib/jenkins/workspace/${JOB_NAME}/KingGizzAPI/target/KingGizzAPI.war /var/lib/wildfly-10.1.0.Final/standalone/deployments/"
                        }
                }
        }
}