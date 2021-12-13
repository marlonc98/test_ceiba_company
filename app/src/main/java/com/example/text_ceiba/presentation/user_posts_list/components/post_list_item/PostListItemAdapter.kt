import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.text_ceiba.R
import com.example.text_ceiba.domain.model.Post

class PostListItemAdapter(val posts: MutableList<Post>): RecyclerView.Adapter<PostListItemAdapter.PostListItemHolder> (){
    class PostListItemHolder(view: View) : RecyclerView.ViewHolder(view){
        val title: TextView
        val body: TextView
        init {
            title = view.findViewById(R.id.title)
            body = view.findViewById(R.id.body)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PostListItemHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.post_list_item, viewGroup, false)

        return PostListItemHolder(view)    }

    override fun onBindViewHolder(holder: PostListItemHolder, position: Int) {
        holder.title.text = posts[position].title
        holder.body.text = posts[position].body

    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
