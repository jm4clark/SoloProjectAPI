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
                                sh "mvn clean -f /var/lib/jenkins/workspace/ProjectPipeline/KingGizzAPI"
                        }
                }
                stage('--test--'){
                        steps{
                                sh "mvn test -f /var/lib/jenkins/workspace/ProjectPipeline/KingGizzAPI"
                        }
                }
                stage('--package--'){
                        steps{
                                sh "mvn package -f /var/lib/jenkins/workspace/ProjectPipeline/KingGizzAPI"
                        }
                }
		stage('--sonar--'){
                        steps{
                                sh "mvn sonar:sonar -f /var/lib/jenkins/workspace/ProjectPipeline/KingGizzAPI"
                        }
                }
		stage('--verify--'){
                        steps{
                                sh "mvn verify -f /var/lib/jenkins/workspace/ProjectPipeline/KingGizzAPI"
                        }
                }
		stage('--surefire--'){
                        steps{
                                sh "mvn surefire-report:report -f /var/lib/jenkins/workspace/ProjectPipeline/KingGizzAPI"
				sh "mvn site -f /var/lib/jenkins/workspace/ProjectPipeline/KingGizzAPI"
                        }
                }
		stage('--deploy--'){
                        steps{
                                sh "cd /"
				sh "pwd"
				sh "sudo cp /var/lib/jenkins/workspace/ProjectPipeline/KingGizzAPI/target/KingGizzAPI.war /var/lib/wildfly-10.1.0.Final/standalone/deployments/"
                        }
                }
        }
}