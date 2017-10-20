/*
 * @author Orla MacLean (ocm4) && Aaron Rosenfeld (asr254)
 */
package P2;

import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.InverseExpression;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;

import java.util.ArrayList;
import java.util.Stack;

import net.sf.jsqlparser.expression.*;

/**
* Implements ExpressionVisitor interface from Jsqlparser package in order
* to evaluate Expressions.
* 
* Leaf Expressions -- columns or long expressions
* 
* Processes children expressions upward from leaf expressions evaluating using an
* operand stack and relevant expressions. The recursive step is to and the result
* of the child evaluation with the result of the current expression in order to produce
* a new result value and return this through all expression visits. 
*/

public class Expressions implements ExpressionVisitor{
	//Schema of the tuples we are checking, given by the tuple's table
	private ArrayList<String> schema;
	//Boolean evaluation of the expression, true if satisfied
	private boolean evalExp;
	//Stack of operands to process
	Stack<Integer> stackOps;
	//Tuple we are evaluating for satisfying the expression condition.
	private Tuple tuple;
	
	/**Constructor for Expressions. Sets a fresh operand stack with evalExp
	 * initialized  as true for the given schema.
	 * @param schema -- Schema of the table for which the tuples being 
	 * evaluated belong
	 */
	public Expressions(ArrayList<String> schema) {
		// TODO Auto-generated constructor stub
		this.evalExp = true;
		this.stackOps = new Stack<Integer>();
		this.schema = schema;
	}

	//@returns the evaluation of the expression
	public boolean getEvalExp() {
		return evalExp;
	}
	
	/** 
	 * Resets necessary values for evauating a new tuple, 
	 * evalExp becomes true again and the stack is cleared 
	 * for the new tuple given.
	 * @param tuple -- new tuple to evaluate expression on.
	 */
	public void reset(Tuple tuple) {
		this.tuple = tuple;
		this.evalExp = true;
		this.stackOps.clear();
	}
	
	@Override
	public void visit(NullValue arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Function arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InverseExpression arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(JdbcParameter arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DoubleValue arg0) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Gets number from long expression visited 
	 *  and places it as an int on the operands stack.
	 */
	@Override
	public void visit(LongValue arg0) {
		Integer i = (int) arg0.getValue();
		stackOps.push(i);
	}

	@Override
	public void visit(DateValue arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TimeValue arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TimestampValue arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Parenthesis arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StringValue arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Addition arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Division arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Multiplication arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Subtraction arg0) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 *  Accepts left and right subexpressions to evaluate
	 *  an and expression visitation. 
	 */
	@Override
	public void visit(AndExpression arg0) {
		// TODO Auto-generated method stub
		Expression left = arg0.getLeftExpression();
		Expression right = arg0.getRightExpression();
		left.accept(this);
		right.accept(this);
	}

	@Override
	public void visit(OrExpression arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Between arg0) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * Accepts left and right subchildren to add to the
	 * expressions evaluation depending on if left
	 * child is equal to the right child. 
	 */
	@Override
	public void visit(EqualsTo arg0) {
		// TODO Auto-generated method stub
		Expression left = arg0.getLeftExpression();
		Expression right = arg0.getRightExpression();
		left.accept(this);
		right.accept(this);
		evalExp = evalExp && (stackOps.pop() == stackOps.pop()); 
	}

	/** 
	 *  Accepts left and right child expressions then updates evalExp
	 *  depending on if the left is greater than the right. 
	 */
	@Override
	public void visit(GreaterThan arg0) {
		// TODO Auto-generated method stub
		Expression left = arg0.getLeftExpression();
		Expression right = arg0.getRightExpression();
		left.accept(this);
		right.accept(this);
		evalExp = evalExp && (stackOps.pop() < stackOps.pop()); 
	}

	/** 
	 *  Accepts left and right child expressions then updates evalExp
	 *  depending on if the left is greater than or equal to the right. 
	 */
	@Override
	public void visit(GreaterThanEquals arg0) {
		// TODO Auto-generated method stub
		Expression left = arg0.getLeftExpression();
		Expression right = arg0.getRightExpression();
		left.accept(this);
		right.accept(this);
		evalExp = evalExp && (stackOps.pop() <= stackOps.pop()); 
	}

	@Override
	public void visit(InExpression arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IsNullExpression arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(LikeExpression arg0) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 *  Accepts left and right child expressions then updates evalExp
	 *  depending on if the left is less than the right. 
	 */
	@Override
	public void visit(MinorThan arg0) {
		// TODO Auto-generated method stub
		Expression left = arg0.getLeftExpression();
		Expression right = arg0.getRightExpression();
		left.accept(this);
		right.accept(this);
		evalExp = evalExp && (stackOps.pop() > stackOps.pop()); 
	}

	/** 
	 *  Accepts left and right child expressions then updates evalExp
	 *  depending on if the left is less than or equal to the right. 
	 */
	@Override
	public void visit(MinorThanEquals arg0) {
		// TODO Auto-generated method stub
		Expression left = arg0.getLeftExpression();
		Expression right = arg0.getRightExpression();
		left.accept(this);
		right.accept(this);
		evalExp = evalExp && (stackOps.pop() >= stackOps.pop()); 
	}

	/** 
	 *  Accepts left and right child expressions then updates evalExp
	 *  depending on if the left is not equal to the right. 
	 */
	@Override
	public void visit(NotEqualsTo arg0) {
		// TODO Auto-generated method stub
		Expression left = arg0.getLeftExpression();
		Expression right = arg0.getRightExpression();
		left.accept(this);
		right.accept(this);
		evalExp = evalExp && (stackOps.pop() != stackOps.pop()); 
	}

	/** 
	 *  Pushes element from tuple to operands stack given
	 *  the column name it corresponds to in the schema.
	 *  @param arg0 -- valid column name in given schema
	 */
	@Override
	public void visit(Column arg0) {
		// TODO Auto-generated method stub
		String name = arg0.getWholeColumnName();
		int i = schema.indexOf(name);
		stackOps.push(tuple.getElement(i));
	}

	@Override
	public void visit(SubSelect arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CaseExpression arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(WhenClause arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ExistsExpression arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AllComparisonExpression arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AnyComparisonExpression arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Concat arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Matches arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BitwiseAnd arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BitwiseOr arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BitwiseXor arg0) {
		// TODO Auto-generated method stub
		
	}

}
