#!/bin/bash
set -e

echo "=========================================================="
echo "Starting HRMS API Server Setup on Amazon Linux 2023..."
echo "=========================================================="

# Create application directory and environment secrets file
mkdir -p /opt/hrms
cat << 'EOF' > /opt/hrms/.env
SPRING_DATASOURCE_URL=${db_url}
SPRING_DATASOURCE_USERNAME=${db_username}
SPRING_DATASOURCE_PASSWORD=${db_password}
SPRING_JPA_DATABASE_PLATFORM=${db_platform}
JWT_SECRET=${jwt_secret}
PORT=8080
EOF

chmod 600 /opt/hrms/.env
chown -R ec2-user:ec2-user /opt/hrms

echo "=========================================================="
echo "Setup completed! Environment variables are ready at /opt/hrms/.env"
echo "=========================================================="
