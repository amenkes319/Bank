package application;

public class Crypto
{
    public static byte[] encrypt(byte[] data)
    {
        byte[] enc = new byte[data.length];

        for(int i = 0; i < enc.length; i++)
        {
            enc[i] = (byte) (i % 2 == 0 ? data[i] + 1 : data[i] - 1) ;
        }

        return enc;
    }

    public static byte[] decrypt(byte[] data)
    {
        byte[] enc = new byte[data.length];

        for(int i = 0; i < enc.length; i++)
        {
            enc[i] = (byte) (i % 2 == 0 ? data[i] - 1 : data[i] + 1) ;
        }

        return enc;
    }
}
