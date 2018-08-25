package edu.orangecoastcollege.cs273.dreyna3.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // Currency formatter
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();

    //Connections from the controller to the view
    private EditText mSubtotalEditText;
    private TextView mSubtotalTextView;
    private TextView mTipAmountTextView;
    private TextView mTotalAmountTextView;
    private SeekBar mTipPercentSeekBar;
    private TextView mTipPercentTextView;

    //Connection from the Controller to the Model
    private Bill mBill = new Bill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect widgets with xml views
        mSubtotalEditText = (EditText) findViewById(R.id.subtotalEditText);
        mSubtotalTextView = (TextView) findViewById(R.id.subtotalTextView);
        mTipAmountTextView = (TextView) findViewById(R.id.tipAmountTextView);
        mTotalAmountTextView = (TextView) findViewById(R.id.totalAmountTextView);
        mTipPercentSeekBar = (SeekBar) findViewById(R.id.tipPercentSeekBar);
        mTipPercentTextView = (TextView) findViewById(R.id.seekBarTipPercent);

        mSubtotalEditText.addTextChangedListener(amountTextWatcher);
        mTipPercentSeekBar.setOnSeekBarChangeListener(percentChangeListener);

        //Set default tip to 15% (0.15)
        mBill.setTipPercent(0.15);
    }

    private void updateViews(){
        //Update subtotal text view
        mSubtotalTextView.setText(currency.format(mBill.getSubTotal())); // or mBill.getAmount()
        //Update tip amount text view
        mTipAmountTextView.setText(currency.format(mBill.getTipAmount()));
        //Update total amount text view
        mTotalAmountTextView.setText(currency.format(mBill.getTotalAmount()));
        //Update the tip percent text view
        mTipPercentTextView.setText(percent.format(mBill.getTipPercent()));
    }

    private final TextWatcher amountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Before text is entered and appears on screen
        }

        @Override
        public void onTextChanged(CharSequence text, int i, int i1, int i2) {
            //Right when user enters something and lets go of the key
            try {
                double subtotal = Double.parseDouble(text.toString()) / 100.0;
                mBill.setSubTotal(subtotal);
                //mSubtotalTextView.setText(currency.format(subtotal));

            }
            catch (NumberFormatException e)
            {
                mBill.setSubTotal(0.00);
            }
            //TO-DO:
            // Divide by 100
            // Format as currency

            // Display in TextView
            updateViews();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //After event is complete
        }
    };
    private final SeekBar.OnSeekBarChangeListener percentChangeListener =
            new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
            mBill.setTipPercent(value / 100.0);
            updateViews();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
