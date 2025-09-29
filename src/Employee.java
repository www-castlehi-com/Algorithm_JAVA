public class Employee extends Person {
        private int salary;
        private int annual; // 연차

        public Employee(String name, int age, int salary, int annual) {
            super.name = name;
            super.age = age;
            this.salary = salary;
            this.annual = annual;
        }
        
        @Override
        public void printInfo() {
        	System.out.println("응애 나 아기 printInfo");
        }

        public void printSalary() {
            System.out.println("연차" + annual );
        }
    }