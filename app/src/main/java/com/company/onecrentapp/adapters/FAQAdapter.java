package com.company.onecrentapp.adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.company.onecrentapp.R;

public class FAQAdapter extends BaseAdapter {
    private final String[] questions;
    private final String[] answers;
    private final Context context;

    public FAQAdapter(Context c)
    {
        context = c;
        questions = context.getResources().getStringArray(R.array.faq_questions);
        answers = context.getResources().getStringArray(R.array.faq_answers);
    }

    @Override
    public int getCount() {
        return questions.length;
    }

    @Override
    public Object getItem(int i) {
        return new Pair<>(questions[i], answers[i]);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.faq_item, null);
        }
        ((TextView) view.findViewById(R.id.question)).setText(questions[i]);
        ((TextView) view.findViewById(R.id.answer)).setText(answers[i]);
        return view;
    }
}
