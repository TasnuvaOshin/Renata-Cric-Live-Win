package cricketworldcup.icccricketworldcup.UserEnd.More.Teams;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cricketworldcup.icccricketworldcup.R;
import cricketworldcup.icccricketworldcup.UserEnd.More.Teams.Afganistan.AfganistanFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.Teams.Australia.AustraliaFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.Teams.England.EnglandFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.Teams.India.IndiaFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.Teams.Newzealand.NewzealandFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.Teams.Pakistan.PakistanFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.Teams.SouthAfrica.SouthafricaFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.Teams.Srilanka.SrilankaFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.Teams.Westindies.WestindiesFragment;
import cricketworldcup.icccricketworldcup.UserEnd.More.Teams.bangladesh.bangladeshFragment;

public class TeamFragment extends Fragment implements View.OnClickListener {
private LinearLayout ban,ind,pak,sri,aus,afg,nzw,win,sa,eng;

      private bangladeshFragment bangladeshFragment;
      private IndiaFragment indiaFragment;
      private PakistanFragment pakistanFragment;
      private EnglandFragment englandFragment;
      private SouthafricaFragment southafricaFragment;
      private WestindiesFragment westindiesFragment;
      private AfganistanFragment afganistanFragment;
      private SrilankaFragment srilankaFragment;
      private NewzealandFragment newzealandFragment;
      private AustraliaFragment australiaFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);
       bangladeshFragment = new bangladeshFragment();
       indiaFragment = new IndiaFragment();
       pakistanFragment = new PakistanFragment();
       englandFragment = new EnglandFragment();
       westindiesFragment = new WestindiesFragment();
       southafricaFragment = new SouthafricaFragment();
       srilankaFragment = new SrilankaFragment();
       afganistanFragment = new AfganistanFragment();
       australiaFragment = new AustraliaFragment();
       newzealandFragment = new NewzealandFragment();

       ban = view.findViewById(R.id.ban_layout);
       ind = view.findViewById(R.id.ind_layout);
       sa = view.findViewById(R.id.sa_layout);
       pak = view.findViewById(R.id.pak_layout);
       sri = view.findViewById(R.id.sri_layout);
       afg = view.findViewById(R.id.afg_layout);
       aus = view.findViewById(R.id.aus_layout);
       nzw = view.findViewById(R.id.nzw_layout);
       eng = view.findViewById(R.id.eng_layout);
       win = view.findViewById(R.id.win_layout);
       ban.setOnClickListener(this);
       ind.setOnClickListener(this);
       sa.setOnClickListener(this);
       pak.setOnClickListener(this);
       sri.setOnClickListener(this);
       afg.setOnClickListener(this);
       aus.setOnClickListener(this);
       nzw.setOnClickListener(this);
       eng.setOnClickListener(this);
       win.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ban_layout:
                SetFragment(bangladeshFragment);
                break;

            case  R.id.ind_layout:
                SetFragment(indiaFragment);
                break;

            case  R.id.pak_layout:
                SetFragment(pakistanFragment);
                break;
            case  R.id.win_layout:
                SetFragment(westindiesFragment);
                break;
            case  R.id.eng_layout:
                SetFragment(englandFragment);
                break;
            case  R.id.aus_layout:
                SetFragment(australiaFragment);
                break;
            case  R.id.nzw_layout:
                SetFragment(newzealandFragment);
                break;
            case  R.id.sri_layout:
                SetFragment(srilankaFragment);
                break;
            case  R.id.sa_layout:
                SetFragment(southafricaFragment);
                break;
            case  R.id.afg_layout:
                SetFragment(afganistanFragment);
                break;


        }
    }


    private void SetFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();


    }
}
