package queueImplementation;

public class Playlist {
	public Arraylist<Songs> playList = new Arraylist<>(10);
	public static PriorityQueue<Songs> queue = new PriorityQueue<>();
	
	
	public void viewPlaylist() {
		System.out.println("____________________________________");
		System.out.printf("\n"+"|%-7s | %-20s | \n", "song id", "songs" );
		for (int i=0; i<this.playList.size() && this.playList.get(i) != null; i++) {
			System.out.printf("\n"+"|%-7s | %-20s | \n", this.playList.get(i).id,this.playList.get(i).name);
		}
		System.out.println("------------------------------------");
	}
	
	public static void viewQueue() {
		System.out.println("___________________________________");
		System.out.printf("\n"+"|%-7s | %-20s | \n", "song id", "songs" );
		for (int i=0; i<Playlist.queue.size() && Playlist.queue.get(i) != null; i++) {
			System.out.printf("\n"+"|%-7s | %-20s | \n", Playlist.queue.get(i).id,Playlist.queue.get(i).name);
		}
		System.out.println("------------------------------------");
	}

	public Arraylist<Songs> getPlayList() {
		return playList;
	}

	public void setPlayList(Arraylist<Songs> playList) {
		this.playList = playList;
	}

	public static PriorityQueue<Songs> getQueue() {
		return queue;
	}

	public static void setQueue(PriorityQueue<Songs> queue) {
		Playlist.queue = queue;
	}

	public static void playSong() {
		for (Songs song: queue) {
			if (song != null) {
				System.out.println(song.name+ " is playing..........\n");
				Playlist.queue.poll();
			}
		}
	}
	
	public void addSongToPlaylist(Songs song) {
		this.playList.add(song);
	}
}
