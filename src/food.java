public class food {


private String code;    //Code of the food
private String name;    //Name of the food

    /**
     * Each food available in the restaurant
     * @param code of the new food
     * @param name of the new food
     */
    public food(String code, String name){
        this.code = code;
        this.name = name;
    }

    /**
     * @return the name of the food
     */
    public String name(){
        return name;
    }

    /**
     * @return the code of the food
     */
    public String code(){
        return code;
    }
}