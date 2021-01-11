package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.*;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseManagerTest {

    @Test
    public void testcalculateTotal(){

        ExpenseRepository mockExpenseRepository = mock(ExpenseRepository.class);
        List<Expense> expenses = new ArrayList<>();
        Expense expense1 = new Expense();
        Expense expense2=new Expense();
        Expense expense3=new Expense();
        expense1.setAmount(0);
        expense2.setAmount(1);
        expense3.setAmount(2);
        expenses.add(expense1);
        expenses.add(expense2);
        expenses.add(expense3);
        when(mockExpenseRepository.getExpenses()).thenReturn(expenses);
        ExpenseManager expenseManager = new ExpenseManager(mockExpenseRepository, new FancyService());
        assertEquals(3,expenseManager.calculateTotal());
    }

    @Test
    public void testcalculateTotalForCategory(){
        ExpenseRepository mockExpenseRepository = mock(ExpenseRepository.class);
        List<Expense> expensesCar = new ArrayList<>();
        List<Expense> expensesHome = new ArrayList<>();
        Expense expenseCar1 = new Expense();
        Expense expenseCar2=new Expense();
        Expense expenseCar3=new Expense();
        Expense expenseHome1 = new Expense();
        Expense expenseHome2=new Expense();
        Expense expenseHome3=new Expense();
        expenseCar1.setAmount(0);
        expenseCar2.setAmount(1);
        expenseCar3.setAmount(2);
        expenseHome1.setAmount(3);
        expenseHome2.setAmount(4);
        expenseHome3.setAmount(5);
        expensesCar.add(expenseCar1);
        expensesCar.add(expenseCar2);
        expensesCar.add(expenseCar3);
        expensesHome.add(expenseHome1);
        expensesHome.add(expenseHome2);
        expensesHome.add(expenseHome3);
        when(mockExpenseRepository.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(mockExpenseRepository.getExpensesByCategory("Car")).thenReturn(expensesCar);
        when(mockExpenseRepository.getExpensesByCategory("Home")).thenReturn(expensesHome);
        //when(mockExpenseRepository.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        //(5.1)kolejność ma znaczenie, w takim wypadku home oraz car również by zwracały pustą listę
        ExpenseManager expenseManager = new ExpenseManager(mockExpenseRepository, new FancyService());
        assertEquals(3,expenseManager.calculateTotalForCategory("Car"));
        assertEquals(12,expenseManager.calculateTotalForCategory("Home"));
        assertEquals(0,expenseManager.calculateTotalForCategory("Food"));
        assertEquals(0,expenseManager.calculateTotalForCategory("Sport"));
    }

    @Test
    public void testcalculateTotalInDollars() throws ConnectException {
        ExpenseRepository mockExpenseRepository = mock(ExpenseRepository.class);
        FancyService mockService =mock(FancyService.class);
        List<Expense> expenses = new ArrayList<>();
        Expense expense1 = new Expense();
        Expense expense2=new Expense();
        Expense expense3=new Expense();
        expense1.setAmount(1);
        expense2.setAmount(3);
        expense3.setAmount(4);
        expenses.add(expense1);
        expenses.add(expense2);
        expenses.add(expense3);
        when(mockExpenseRepository.getExpenses()).thenReturn(expenses);
        ExpenseManager expenseManager = new ExpenseManager(mockExpenseRepository, mockService);
        //when(mockService.convert(8,"PLN","USD")).thenReturn((double)(2));
        when(mockService.convert(8,"PLN","USD")).thenThrow(new ConnectException());
        //verify(mockService).convert(anyDouble(), eq("PLN"), eq("USD"));
        assertEquals(-1,expenseManager.calculateTotalInDollars());
    }

    @Test
    public void testcalculateTotalInDollars_9() throws ConnectException {
        ExpenseRepository mockExpenseRepository = mock(ExpenseRepository.class);
        FancyService mockService =mock(FancyService.class);
        List<Expense> expenses = new ArrayList<>();
        Expense expense1 = new Expense();
        Expense expense2=new Expense();
        Expense expense3=new Expense();
        expense1.setAmount(1);
        expense2.setAmount(3);
        expense3.setAmount(4);
        expenses.add(expense1);
        expenses.add(expense2);
        expenses.add(expense3);
        when(mockExpenseRepository.getExpenses()).thenReturn(expenses);
        ExpenseManager expenseManager = new ExpenseManager(mockExpenseRepository, mockService);
        when(mockService.convert(8,"PLN","USD")).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation){
                        Object[] args = invocation.getArguments();
                        return Double.parseDouble(args[0].toString())/4;
                    }
                }
        );
        assertEquals(2,expenseManager.calculateTotalInDollars());
    }

}
