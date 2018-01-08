package com.prepare.luxoft;

public class Test2 {

    /**
     * В интерфейсе можно объявлять поля НО они должны быть обизательно проинициализированны И НЕдолжны быть приватными!
     * (по умолчанию все поля в интерфейсе являются статическими и публичными и финальными)
     */
    public interface MyC {
//        public static final int a; // ошибка - нет иннициализации
//        private int b = 100; // ошибка - нельзя приватное поле
        int a = 100; // нормально!
        String str = "abc"; // нормально!
        Aaa aaa = new Aaa(); // нормально!

        class Aaa {
            private int ego;
            private String name;
            public int getEgo() {
                return ego;
            }
            public void setEgo(int ego) {
                this.ego = ego;
            }
            public String getName() {
                return name;
            }
            public void setName(String name) {
                this.name = name;
            }
        }
    }

}
