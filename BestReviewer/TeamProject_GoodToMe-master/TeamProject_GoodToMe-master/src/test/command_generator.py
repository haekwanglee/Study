import datetime
from faker import Faker
from enum import Enum, auto
import random
from dataclasses import dataclass
import sys


# ADD, , , ,18050301,KYUMOK KIM,CL2,010-9777-6055,19980906,ADV
class Command(Enum):
    ADD = "ADD"
    MOD = "MOD"
    DEL = "DEL"
    SCH = "SCH"


def randemployeenumber():
    year = random.randrange(1990, 2020) % 100
    number = random.randrange(0, 999999 + 1)
    return f'{year:02}{number:06}'


def randname():
    fake = Faker()
    return fake.name()


def randcl():
    levels = ['CL1', 'CL2', 'CL3', 'CL4']
    return random.choice(levels)


def randcertification():
    cert = ['ADV', 'PRO', 'EX']
    return random.choice(cert)


def randphonenumber():
    middle = random.randrange(0, 9999)
    last = random.randrange(0, 9999)
    return f'010-{middle:04}-{last:04}'


def randbirthday():
    start_date = datetime.date(1900, 1, 1)
    end_date = datetime.date(2019, 1, 1)

    time_between_dates = end_date - start_date
    days_between_dates = time_between_dates.days
    random_number_of_days = random.randrange(days_between_dates)
    random_date = start_date + datetime.timedelta(days=random_number_of_days)
    return random_date.strftime('%Y%m%d')


def rand_option1():
    option_list = [" ", "-p"]
    return random.choice(option_list)


def rand_option2(field):
    option_list = [" "]
    if field == EmployeeStoreField.NAME.value:
        option_list += ["-f", "-l"]
    elif field == EmployeeStoreField.PHONE_NUM.value:
        option_list += ["-m", "-l"]
    elif field == EmployeeStoreField.BIRTHDAY.value:
        option_list += ["-y", "-m", "-d"]
    return random.choice(option_list)


def rand_option3():
    option_list = [" ", "-g", "-ge", "-s", "-se"]
    return random.choice(option_list)


class EmployeeStoreField(Enum):
    EMPLOYEE_NUMBER = "employeeNum"
    NAME = "name"
    CL = "cl"
    PHONE_NUM = "phoneNum"
    BIRTHDAY = "birthday"
    CERT = "certi"


@dataclass
class Employee:
    employee_number: str
    name: str
    cl: str
    phone_number: str
    birthday: str
    cert: str


employees = []


def get_field_value(option, field, employee):
    if field == EmployeeStoreField.NAME.value:
        tokens = employee.name.split(" ")
        if option == "-f":
            return tokens[0]
        if option == "-l":
            return tokens[1]
        else:
            return employee.name
    if field == EmployeeStoreField.EMPLOYEE_NUMBER.value:
        return employee.employee_number
    if field == EmployeeStoreField.CL.value:
        return employee.cl
    if field == EmployeeStoreField.PHONE_NUM.value:
        tokens = employee.phone_number.split("-")
        if option == "-m":
            return tokens[1]
        if option == "-l":
            return tokens[2]
        else:
            return employee.phone_number
    if field == EmployeeStoreField.BIRTHDAY.value:
        if option == "-y":
            return employee.birthday[0:4]
        if option == "-m":
            return employee.birthday[4:6]
        if option == "-d":
            return employee.birthday[6:8]
        else:
            return employee.birthday
    if field == EmployeeStoreField.CERT.value:
        return employee.cert


def rand_value_as_field(field):
    if field == EmployeeStoreField.NAME.value:
        return randname()
    if field == EmployeeStoreField.EMPLOYEE_NUMBER.value:
        return randemployeenumber()
    if field == EmployeeStoreField.CL.value:
        return randcl()
    if field == EmployeeStoreField.PHONE_NUM.value:
        return randphonenumber()
    if field == EmployeeStoreField.BIRTHDAY.value:
        return randbirthday()
    if field == EmployeeStoreField.CERT.value:
        return randcertification()


def generate_command(command, join=""):
    tokens = []
    tokens.append(command.value)
    if command == Command.ADD:
        employee_number = randemployeenumber()
        name = randname()
        cl = randcl()
        phone_number = randphonenumber()
        birthday = randbirthday()
        certi = randcertification()

        employees.append(Employee(employee_number, name, cl, phone_number, birthday, certi))

        tokens += [" , , ", employee_number, name, cl, phone_number, birthday, certi]

    else:
        field = random.choice(list(EmployeeStoreField)).value
        field2 = random.choice(list(EmployeeStoreField)).value
        option = rand_option2(field)
        option2 = rand_option2(field)

        random_employee = random.choice(employees)

        value = get_field_value(option, field, random_employee, )
        value2 = get_field_value(option2, field2, random_employee)

        new_employee_field = [EmployeeStoreField.PHONE_NUM.value,
                              EmployeeStoreField.BIRTHDAY.value,
                              EmployeeStoreField.CL.value,
                              EmployeeStoreField.NAME.value,
                              EmployeeStoreField.CERT.value]
        new_field = random.choice(new_employee_field)
        new_value = rand_value_as_field(new_field)

        tokens.append(rand_option1())
        tokens += rand_query_token(option, field, value)

        if (join == "-a") or (join == "-o"):
            tokens.append(join)
            tokens += rand_query_token(option2, field2, value2)
        if command == Command.MOD:
            tokens += [new_field, new_value]
    return ",".join(tokens)


def rand_query_token(option, field, value):
    return [option, rand_option3(), field, value]


f = open("output.txt", "w")
# sys.stdout = f

for i in range(200):
    print(generate_command(Command.ADD))

for i in range(5):
    print(generate_command(Command.DEL))

for i in range(5):
    print(generate_command(Command.SCH))

for i in range(5):
    print(generate_command(Command.MOD))

for i in range(3):
    print(generate_command(Command.DEL, "-a"))
    print(generate_command(Command.DEL, "-o"))
    print(generate_command(Command.SCH, "-a"))
    print(generate_command(Command.SCH, "-o"))
    print(generate_command(Command.MOD, "-a"))
    print(generate_command(Command.MOD, "-o"))

f.close()
