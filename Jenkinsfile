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
                                sh "mvn clean -f /var/lib/jenkins/workspace/pipeline/KingGizzAPI"
                        }
                }
                stage('--test--'){
                        steps{
                                sh "mvn test -f /var/lib/jenkins/workspace/pipeline/KingGizzAPI"
                        }
                }
                stage('--package--'){
                        steps{
                                sh "mvn package -f /var/lib/jenkins/workspace/pipeline/KingGizzAPI"
                        }
                }
		stage('--sonar--'){
                        steps{
                                sh "mvn sonar:sonar -f /var/lib/jenkins/workspace/pipeline/KingGizzAPI"
                        }
                }
		stage('--verify--'){
                        steps{
                                sh "mvn verify -f /var/lib/jenkins/workspace/pipeline/KingGizzAPI"
                        }
                }
		stage('--surefire--'){
                        steps{
                                sh "mvn surefire-report:report -f /var/lib/jenkins/workspace/pipeline/KingGizzAPI"
				sh "mvn site -f /var/lib/jenkins/workspace/pipeline/KingGizzAPI"
                        }
                }
		stage('--deploy--'){
                        steps{
                                sh "cd /"
				sh "pwd"
				sh "sudo cp /var/lib/jenkins/workspace/pipeline/KingGizzAPI/target/KingGizzAPI.war /home/jamesmclark96/wildfly-10.1.0.Final/standalone/deployments/"
                        }
                }
        }
}