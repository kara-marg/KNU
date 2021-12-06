#include <iostream>
#include <vector>


#include "lab1.1.h"

void test1()
{
	CREATING_AND_FILLING_THE_FILE_WITH_MATRIX_ELEMENTS(); 	// СОЗДАНИЕ И ЗАПОЛНЕНИЕ ФАЙЛА ЭЛЕМЕНТАМИ МАТРИЦЫ

	MatrixElement_List<double> horizontal_A[order];
	matrix_creation(horizontal_A, "A"); //заполняем матрицу А с файла и печатаем
	std::cout << std::endl << std::endl;

	search_by_index_method_main(horizontal_A); //поиск элемента по индексу, вызывает метод класса MatrixElement_List

	std::cout << std::endl;
	search_by_index_function_main(horizontal_A); //поиск элемента по индексу, вызывает функцию

	search_first_by_value_main(horizontal_A); 	//поиск первого элемента по значению

	search_all_by_value_main(horizontal_A); 	//поиск всех элементов по значению

	search_first_element_by_condition(horizontal_A); //поиск первого элемента по заданному условию


	MatrixElement_List<double> horizontal_B[order];
	matrix_creation(horizontal_B, "B");		//заполняем матрицу В с файла и печатаем
	std::cout << std::endl << std::endl;

	matrix_sum_main(horizontal_A, horizontal_B); //сумма матриц

	matrix_multiplication_main(horizontal_A, horizontal_B); 	//умножение матриц

	matrix_transpose_main(horizontal_A);	//транспонирование матрицы А

	matrix_multiplication_by_vector_main(horizontal_A);	//умножение матрицы А на вектор
}


int main()
{
	setlocale(LC_ALL, "");

	test1();
}