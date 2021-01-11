package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.jupiter.api.Test;

import org.mockito.InOrder;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.Collections;
public class ExpenseRepositoryTest {

    ExpenseRepository expenseRepository;
    IFancyDatabase fancyDatabase;
    Expense expense;

    @Test
    public void testloadExpenses_1() {
        //fancyDatabase = new FancyDatabase();
        fancyDatabase = new MyDatabase();
        expenseRepository = new ExpenseRepository(fancyDatabase);
        expenseRepository.loadExpenses();
        assertEquals(0,expenseRepository.getExpenses().size());
    }
    @Test
    public void testloadExpenses_2() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        expenseRepository = new ExpenseRepository(mockDatabase);
        expenseRepository.loadExpenses();
        when(mockDatabase.queryAll()).thenReturn(Collections.emptyList());
        InOrder inOrder = inOrder(mockDatabase);
        inOrder.verify(mockDatabase).connect();
        inOrder.verify(mockDatabase).queryAll();
        inOrder.verify(mockDatabase).close();
        assertEquals(0,expenseRepository.getExpenses().size());
    }

    @Test
    public void testloadExpenses_3() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        expenseRepository = new ExpenseRepository(mockDatabase);
        expenseRepository.loadExpenses();
        //expenseRepository.addExpense(new Expense());
        expense = new Expense();
        expenseRepository.addExpense(expense);
        expenseRepository.saveExpenses();
        //verify(mockDatabase).persist(Expense.class);
        //verify(mockDatabase).persist(expense);
        verify(mockDatabase).persist(any(Expense.class));
        InOrder inOrder = inOrder(mockDatabase);
        inOrder.verify(mockDatabase).connect();
        inOrder.verify(mockDatabase).queryAll();
        inOrder.verify(mockDatabase).close();
    }

    @Test
    public void testloadExpenses_3_5() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        expenseRepository = new ExpenseRepository(mockDatabase);
        expenseRepository.loadExpenses();
        expense = new Expense();
        for (int i = 0; i < 5; i++) {
            expenseRepository.addExpense(expense);
        }
        expenseRepository.saveExpenses();
        //verify(mockDatabase).persist(Expense.class);
        //verify(mockDatabase).persist(expense);
        verify(mockDatabase, times(5)).persist(any(Expense.class));
        InOrder inOrder = inOrder(mockDatabase);
        inOrder.verify(mockDatabase).connect();
        inOrder.verify(mockDatabase).queryAll();
        inOrder.verify(mockDatabase).close();
    }
}
