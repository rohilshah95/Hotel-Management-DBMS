public static class Bill
{
  static int id = 4; // because demo data already has 4 entries
  void createBill(String id, String amount, String modeOfPayment, String discount)
  {
    try
    {
      id++;
      //create entry


    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }

  void calcBill(String custId, String checkOutDate)
  {
    try
    {
      //create entry
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }

  void generateReceipt(String custId, checkOutDate)
  {
    try
    {
      //create entry
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }

}
