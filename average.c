#include <stdio.h>

float average(int a, int b) {
    return (a + b) / 2.0;
}

int main() {
    int num1, num2;
    float result;

    printf("Enter two integers: ");
    scanf("%d %d", &num1, &num2);

    result = average(num1, num2);

    printf("The average of %d and %d is %.2f\n", num1, num2, result);

    return 0;
}
