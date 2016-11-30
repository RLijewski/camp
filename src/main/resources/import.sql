-- Insert role
INSERT INTO role (name) VALUES ('ROLE_APP_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_USER');

-- Insert users
INSERT INTO user (username,enabled,password,role_id) VALUES ('app-admin',true,'$2a$08$3Dp04dSxFPHg3KuqHVAqfetbvWk1bI3FqzSY2NXP22YFDOhNdrsFe',1);
INSERT INTO user (username,enabled,password,role_id) VALUES ('user',true,'$2a$08$ESS.gVpDlgtUr.eqGLuQEeAhGEH4HZtCJCzl9rVnlZJ64AJQ1vq4S',2);
INSERT INTO user (username,enabled,password,role_id) VALUES ('user2',true,'$2a$08$ESS.gVpDlgtUr.eqGLuQEeAhGEH4HZtCJCzl9rVnlZJ64AJQ1vq4S',2);