import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.text_ceiba.R
import com.example.text_ceiba.domain.model.User

class UserListItemAdapter(val users: MutableList<User>): RecyclerView.Adapter<UserListItemAdapter.UserListItemHolder> (){
    class UserListItemHolder(view: View) : RecyclerView.ViewHolder(view){
        val phone: TextView
        val name: TextView
        val email: TextView
        val btnViewPost: Button
        init {
            phone = view.findViewById(R.id.phone)
            name = view.findViewById(R.id.name)
            email = view.findViewById(R.id.email)
            btnViewPost = view.findViewById(R.id.btn_view_post)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserListItemHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.user_list_item, viewGroup, false)

        return UserListItemHolder(view)    }

    override fun onBindViewHolder(holder: UserListItemHolder, position: Int) {
        holder.name.text = users[position].name
        holder.phone.text = users[position].phone
        holder.email.text = users[position].email
        holder.btnViewPost.setOnClickListener {
            TODO("Not yet implemented")
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}
