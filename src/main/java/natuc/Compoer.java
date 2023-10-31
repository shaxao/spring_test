package natuc;

public class Compoer {
    public static void main(String[] args) {
        int[] dd = {0,0,1,0,0,0,0,1,0,1,0,0,0,1,0,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0};
        boolean b = new Compoer().canPlaceFlowers(dd, 17);
        System.out.println(b);
        //00100001010001001010100010101001000001010001001000100100100010000100100001000101000000
        //10101001010101001010101010101001010101010101001010100100101010100100101001010101010101
        //10101001011001101010101010101001101001011001101010100110101010100110101001100101101001
    }
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        //先判断首末节点，如果有不同则设为不同值
        //然后开始循环遍历，如果有三个连续的元素为0，n+1，将中间元素变值后重新遍历
        int result = 0;
        if(flowerbed.length == 1){
            if(flowerbed[0] == 1){
                if(n == 0){
                    return true;
                }
            }else{
                if(n <= 1){
                    return true;
                }
            }
            return false;
        }
        if(flowerbed[0] == flowerbed[1] && flowerbed[0] == 0){
            flowerbed[0] = 1;
            result++;
        }
        if(flowerbed[flowerbed.length - 1] == flowerbed[flowerbed.length - 2] && flowerbed[flowerbed.length - 1] == 0){
            flowerbed[flowerbed.length - 1] = 1;
            result++;
        }
        int count = 0;
        for(int i = 0;i < flowerbed.length;i++){
            if(i == flowerbed.length - 1){
                break;
            }
            if(flowerbed[i] == flowerbed[i+1] && flowerbed[i] == 0){
                count++;
            }
            if(count == 2){
                flowerbed[i] = 1;
                result++;
                count = 0;
            }
        }
        for(int b = 0;b < flowerbed.length;b++){
            System.out.print(flowerbed[b]);
        }
        if(n <= result){
            System.out.println(result);
            return true;
        }
        return false;
    }
}
