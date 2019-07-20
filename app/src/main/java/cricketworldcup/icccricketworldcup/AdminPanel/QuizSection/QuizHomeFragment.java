package cricketworldcup.icccricketworldcup.AdminPanel.QuizSection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cricketworldcup.icccricketworldcup.AdminPanel.QuizSection.InstantQuiz.InstantQuiz;
import cricketworldcup.icccricketworldcup.AdminPanel.QuizSection.MatchPrediction.MatchPrediction;
import cricketworldcup.icccricketworldcup.AdminPanel.QuizSection.SeenQuiz.SeenQuiz;
import cricketworldcup.icccricketworldcup.R;

public class QuizHomeFragment extends Fragment {
    private Button q1, q2, q3;
    private SeenQuiz seenQuiz;
    private MatchPrediction matchPrediction;
    private InstantQuiz instantQuiz;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_home, container, false);
        q1 = view.findViewById(R.id.q1);
        q2 = view.findViewById(R.id.q2);
        q3 = view.findViewById(R.id.q3);


        seenQuiz = new SeenQuiz();
        instantQuiz = new InstantQuiz();
        matchPrediction = new MatchPrediction();
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(seenQuiz);
            }
        });

        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(instantQuiz);
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(matchPrediction);
            }
        });
        return view;
    }

    private void SetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();


    }
}
