package br.com.cams7.app;

public class ReportView {

	private IntervalPages interval;

	private int firstPage;
	private int lastPage;
	private int totalPages;

	public ReportView() {
		super();
	}

	public IntervalPages getInterval() {
		return interval;
	}

	public void setInterval(IntervalPages interval) {
		this.interval = interval;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	public String toString() {
		return String.format("%s{interval:%s, firstPage:%s, lastPage:%s, totalPages:%s}",
				this.getClass().getSimpleName(), getInterval(), getFirstPage(), getLastPage(), getTotalPages());
	}

	public enum IntervalPages {
		ALL_PAGES, INFORMED_INTERVAL, CURRENT_PAGE
	}

}
