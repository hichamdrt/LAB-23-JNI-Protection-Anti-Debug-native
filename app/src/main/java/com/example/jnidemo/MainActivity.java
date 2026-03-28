package com.example.jnidemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log; // Daroriya bach t-chouf l-logs
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public native String helloFromJNI();
    public native int factorial(int n);
    public native String reverseString(String s);
    public native int sumArray(int[] values);

    static {
        System.loadLibrary("jnidemo");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvHello = findViewById(R.id.tvHello);
        TextView tvFact = findViewById(R.id.tvFact);
        TextView tvReverse = findViewById(R.id.tvReverse);
        TextView tvArray = findViewById(R.id.tvArray);

        // --- TEST 1 : Valeur Normale ---
        int f10 = factorial(10);
        tvHello.setText("Test 1 (10!): " + f10); // Khass tlqa 3628800

        // --- TEST 2 : Valeur Négative ---
        int fNeg = factorial(-5);
        tvFact.setText("Test 2 (-5!): " + fNeg); // Khass tlqa -1 (Error)

        // --- TEST 3 : Dépassement (Overflow) ---
        int f20 = factorial(20);
        tvReverse.setText("Test 3 (20!): " + f20); // Khass tlqa -2 (Overflow)

        // --- TEST 4 : Chaîne Vide ---
        String emptyStr = reverseString("");
        // Hna ghadi n-zidou Test 5 m3aha f nfs l-TextView bach t-ban
        tvArray.setText("Test 4 (Vide): '" + emptyStr + "'");

        // --- TEST 5 : Tableau Vide ---
        int sumEmpty = sumArray(new int[]{});
        // Ghadi n-afichiw natija f Logcat bach mat-khletch lik m3a l-interface
        Log.d("JNI_TESTS", "Test 5 (Array empty) result: " + sumEmpty); // Khass tlqa 0
    }
}