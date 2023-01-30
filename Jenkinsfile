pipeline{
    agent any
    options{
        buildDiscarder(logRotator(numToKeepStr: '5', daysToKeepStr: '5'))
        disableConcurrentBuilds()
    }
    parameters{
        string(name: 'imageRepository', defaultValue: '170681985890.dkr.ecr.us-east-2.amazonaws.com/opus-profilemanager:latest', description: 'image repository')
        string(name: 'mongoUser', defaultValue: 'admin', description: 'mongodb root user')
        string(name: 'mongoPassword', defaultValue: 'admin', description: 'mongodb root password')
    }

    stages{
        stage('git pull'){
            steps{
                git 'https://github.com/koffiange62/opus-profilemanager.git'
            }
        }
        // build
        stage('build'){
            steps{
                sh ('./gradlew build -x test')
            }
        }
        // Test
        stage('Test'){
            parallel{
                stage('unit test'){
                    steps{
                        sh ('./gradlew test')
                    }
                }

                stage('Integration test'){
                    stages {
                        stage('Mongodb container'){
                            steps{
                                script{
                                    def mongoImage = docker.image('mongo')
                                    mongoImage.withRun('-e "MONGO_INITDB_ROOT_USERNAME=params.mongoUser" -e "MONGO_INITDB_ROOT_PASSWORD=params.mongoPassword"') { c ->
                                        sh 'echo "lunching opus integration test"'
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        stage('build and push image'){
            steps{
                script{
                    withDockerRegistry(url: 'https://170681985890.dkr.ecr.us-east-2.amazonaws.com', credentialsId: 'ecr:us-east-2:AWS_CREDENTIALS') {
                        def image = docker.build(params.imageRepository)
                        image.push()
                    }
                }
            }
        }
    }
}