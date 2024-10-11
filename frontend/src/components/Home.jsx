import React from 'react';
import './Dashboard.css'; // Assuming CSS is in a separate file

// Header Component
const HeaderComponent = () => {
    return (
        <header className="header">
            <h1>Welcome Back, [User]</h1>
        </header>
    );
};

// Category Cards Component
const CategoryCards = () => {
    const categories = ['Design', 'Development', 'Marketing', 'Business'];
    return (
        <section className="categories">
            {categories.map((category) => (
                <div className="category-card" key={category}>
                    {category}
                </div>
            ))}
        </section>
    );
};

// Trending Courses Component
const TrendingCourses = () => {
    return (
        <section className="trending-courses">
            <h2>Trending Courses</h2>
            <div className="course-cards">
                {/* Replace with dynamic course card */}
                <div className="course-card">Course 1</div>
                <div className="course-card">Course 2</div>
                <div className="course-card">Course 3</div>
            </div>
        </section>
    );
};

// Meetup Section Component
const MeetupSection = () => {
    return (
        <section className="meetup">
            <h2>Join a Meetup</h2>
            <div className="meetup-details">
                Connect with other learners!
            </div>
        </section>
    );
};

// Dashboard Main Component
const Home = () => {
    return (
        <div className="dashboard">
            <HeaderComponent />
            <CategoryCards />
            <TrendingCourses />
            <MeetupSection />
        </div>
    );
};

export default Home;



