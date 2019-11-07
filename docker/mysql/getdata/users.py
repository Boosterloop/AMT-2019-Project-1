from random_username.generate import generate_username
from bcrypt import *

output_file = "../db-data/4_insert-users.sql"
nbr_of_users = 1000

sql_query = "INSERT INTO User (username, passwordHash) VALUES\n"
sql_query += "('luc', '$2a$10$XEdw2VBLYtp72kXQ4tAXku3cVQLsg/06BdlXIy8oL90yueemZ5ZJu'),\n"
sql_query += "('alison', '$2a$10$z8UTnoTbpyWraSPZ/yBeEu6Ldn728C9Eml3KbPE2.9k8ATbq0g9ii'),\n"
sql_query += "('admin', '$2a$10$2RdXQQaOgXjrm4U7MpJeIuD7MGtNxAJhNLNoaphToDhSt4XqxVqbq'),\n"

names = generate_username(nbr_of_users)

for i in range(0, nbr_of_users):
    name = names[i]
    pwd = "password" + str(i)
    hashed = hashpw(pwd.encode('utf-8'), gensalt())
    sql_query += "('{0}', '{1}'),\n".format(name, hashed.decode('utf-8'))

# Remove trailing comma
sql_query = sql_query[:len(sql_query)-2]
sql_query += ";\n"

# with open(output_file, "w+") as writer:
#     writer.write(sql_query)


#################### INSERT VISITS
