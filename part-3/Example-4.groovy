pipeline {
    agent any
        parameters {
            choice(name: 'DEV_DEPLOY', choices: ['Yes', 'No'],
            description: 'Do you want to deploy a development server?')
        }
    stages {
        stage('Compile') {
            input {
                message 'Do you want to run tests?'
                ok 'Yes, we should.'
                parameters {
                    choice(
                        name: 'TURN_ON_TEST',
                        choices: ['Yes', 'No']
                    )
                }
            }
            steps {
                echo 'build'
            }
        }
        stage('Test') {
            when {
                environment name: 'TURN_ON_TEST', value: 'Yes'
            }
            steps {
                echo 'done'
            }
        }
        stage('Deploy to Dev') {
            when {
                environment name: 'DEV_DEPLOY', value: 'Yes'
            }
            steps {
                echo 'Deploying DEPLOY_TO'
            }
        }
    }
}
