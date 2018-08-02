public class TradeDataServiceTest {

    @Test
    public void testQuery()  {
        ClassToTest t  = new ClassToTest(databaseMock);
        boolean check = t.query("* from t");
        assertTrue(check);
        verify(databaseMock).query("* from t");
    }
}
